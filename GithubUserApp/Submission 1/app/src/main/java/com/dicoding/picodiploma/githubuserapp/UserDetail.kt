package com.dicoding.picodiploma.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Detail User"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra(EXTRA_USER) as User

        val image = user.avatar
        val nameUser = user.name.toString()
        val content = ": ${user.username.toString()}\n" +
                ": ${user.company.toString()}\n" +
                ": ${user.location.toString()}\n" +
                ": ${user.repository.toString()}\n" +
                ": ${user.followers.toString()}\n" +
                ": ${user.following.toString()}"
        image?.let{iv_avatar_received.setImageResource(it)}
        tv_name_received.text = nameUser
        tv_object_received.text = content
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
