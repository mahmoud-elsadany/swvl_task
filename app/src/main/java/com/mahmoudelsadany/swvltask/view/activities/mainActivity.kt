package com.mahmoudelsadany.swvltask.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mahmoudelsadany.swvltask.R
import com.mahmoudelsadany.swvltask.view.fragments.searchFragment

class mainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushFragment(searchFragment(), "searchFragment", null)

    }

    fun pushFragment(fragment: Fragment, FragmentTag: String?, bundle: Bundle?) {
        val transaction: FragmentTransaction
        transaction = supportFragmentManager.beginTransaction()
        if (bundle != null) fragment.setArguments(bundle)
        transaction.replace(R.id.containerFrameLayout, fragment, FragmentTag)
        transaction.addToBackStack(FragmentTag)
        transaction.commit()
    }

}
