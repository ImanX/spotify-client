package com.github.imanx.spotify.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.imanx.spotify.R
import com.github.imanx.spotify.databinding.ActivityAuthenticationBinding
import com.github.imanx.spotify.makeToast
import com.github.imanx.spotify.setContentViewByBinding
import com.github.imanx.spotify.startActivity
import com.github.imanx.spotify.utils.SpotifyAuthenticationHelper
import com.github.imanx.spotify.viewModel.AuthenticationViewModel
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity() {

    private val authenticationViewModel by lazy {
        ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentViewByBinding<ActivityAuthenticationBinding>(R.layout.activity_authentication);
        binding.vm = authenticationViewModel;


        authenticationViewModel.getExceptionLiveData().observe(this, exceptionObserver)
        authenticationViewModel.getAuthenticationLiveData().observe(this, authObserver);


        btn_login.setOnClickListener {
            SpotifyAuthenticationHelper(this).startAuthentication()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        authenticationViewModel.onActivityResult(requestCode, resultCode, data)
    }

    private val exceptionObserver = Observer<Exception> {
        makeToast("${getString(R.string.error_message)} ${it.message}")
    }

    private val authObserver = Observer<Unit> {
        startActivity(SearchActivity::class.java)
    }


}
