package com.dicoding.picodiploma.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var users: ArrayList<User> = arrayListOf()
    private lateinit var dataAvatar: TypedArray
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>

    private fun showSelectedUser(user: User) {
        val moveWithObjectIntent =
            Intent(this@MainActivity, UserDetail::class.java)
        moveWithObjectIntent.putExtra(UserDetail.EXTRA_USER, user)
        startActivity(moveWithObjectIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_user.setHasFixedSize(true)

        addItem()
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rv_user.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(users)
        rv_user.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun prepare() {
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
    }

    private fun addItem(): ArrayList<User> {
        prepare()
        for (position in dataName.indices) {
            val user = User(
                dataAvatar.getResourceId(position, -1),
                dataName[position],
                dataUsername[position],
                dataCompany[position],
                dataLocation[position],
                dataRepository[position],
                dataFollowers[position],
                dataFollowing[position]
            )
            users.add(user)
        }
        return users
    }
}
