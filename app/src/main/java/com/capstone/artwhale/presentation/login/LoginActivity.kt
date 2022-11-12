package com.capstone.artwhale.presentation.login

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.ActivityLoginBinding
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.InitialState
import com.capstone.artwhale.presentation.common.Loading
import com.capstone.artwhale.presentation.common.Success
import com.capstone.artwhale.presentation.home.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ArtWhale)
        setTheme(android.R.style.ThemeOverlay_Material_Dark)
        super.onCreate(savedInstanceState)

        initSplashScreen()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGoogleLogin()
        initObserver()
    }

    private fun initObserver() {
        loginViewModel.loginState.onEach {
            when (it) {
                InitialState -> {}
                Loading -> {}
                Success -> startMainActivity()
                is Error -> {
                    Log.d("Tester", "initObserver: ${it.msg}")
                }
            }
        }.launchIn(this.lifecycleScope)
    }

    private fun initSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.navigationBarColor = getColor(R.color.black)
            actionBar?.hide()
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 200L

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd { splashScreenView.remove() }

                // Run your animation.
                slideUp.start()
            }
        }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    var checkLogin: Boolean = true
                    // check already login

                    return if (checkLogin) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        startMainActivity()
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
    }

    private fun initGoogleLogin() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
        binding.btnLogin.setOnClickListener {
            val signIntent: Intent = mGoogleSignInClient.signInIntent
            googleSignResultLauncher.launch(signIntent)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.apply {
                val email = this.email.toString()
                //loginViewModel.login(email)
                startMainActivity()
            }
        } catch (e: ApiException) {
            Log.e("Google account", "signInResult:failed Code = " + e.statusCode)
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            // 로그인이 안되어있는 상태
        } else {
            // 로그인이 되어있는 상태
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}