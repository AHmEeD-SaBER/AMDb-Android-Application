import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.activities.HomeActivity

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stream = view.findViewById<TextView>(R.id.stream)
        val sync = view.findViewById<TextView>(R.id.sync)
        val parentLayout = view.findViewById<ConstraintLayout>(R.id.roote_layout)

        // Ensure that the view is fully measured before starting animations
        view.post {
            // Step 1: Calculate the center position of the parent layout
            val parentWidth = parentLayout.width
            val parentHeight = parentLayout.height
            val streamWidth = stream.width
            val streamHeight = stream.height

            // Center the stream TextView horizontally and vertically
            stream.translationX = (parentWidth / 5f) - (streamWidth / 4f)
            stream.translationY = (parentHeight / 20f) - (streamHeight / 1.5f)

            // Step 2: First Animation - Move stream slightly to the left and fade in sync
            val moveLeftAnimator = ObjectAnimator.ofFloat(stream, "translationX", stream.translationX, stream.translationX - (parentWidth / 4f))
            moveLeftAnimator.duration = 500

            val fadeInSyncAnimator = ObjectAnimator.ofFloat(sync, "alpha", 0f, 1f)
            fadeInSyncAnimator.duration = 500

            val initialAnimatorSet = AnimatorSet().apply {
                playTogether(
                    moveLeftAnimator,
                    fadeInSyncAnimator
                )
                duration = 1200
            }

            // Step 3: Second Animation - Fade out both TextViews and navigate
            val fadeOutBackground = ObjectAnimator.ofFloat(parentLayout, "alpha", 1f, 0f).apply {
                duration = 500
            }

            val moveStreamOut = ObjectAnimator.ofFloat(stream, "translationY", 0f, -view.height.toFloat()).apply {
                duration = 500
            }

            val moveSyncOut = ObjectAnimator.ofFloat(sync, "translationY", 0f, view.height.toFloat()).apply {
                duration = 500
            }

            // Create a second AnimatorSet for exit animations
            val exitAnimatorSet = AnimatorSet().apply {
                playTogether(
                    fadeOutBackground,
                    moveStreamOut,
                    moveSyncOut
                )
            }

            // Add listeners for completing animations and navigating
            initialAnimatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    exitAnimatorSet.start()
                }
            })

            exitAnimatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
                    val userId = sharedPreferences.getString("userId", null)
                    if(userId != null){
                        // Navigate to HomeActivity and finish the SignupActivity
                        val homeIntent = Intent(requireContext(), HomeActivity::class.java)
                        startActivity(homeIntent)
                        requireActivity().finish()  // Close the SignupActivity
                    }
                    else {
                        // Navigate to the next fragment after the animation ends
                        findNavController().navigate(R.id.welcomeFragment)
                    }
                }
            })

            // Start the first set of animations after a small delay
            Handler().postDelayed({
                sync.visibility = View.VISIBLE
                initialAnimatorSet.start()
            }, 200) // Delay to start the animation
        }
    }
}
