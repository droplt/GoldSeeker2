package com.example.bd0631.goldseeker

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
  supportFragmentManager.transact {
    add(frameId, fragment)
  }
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
  setSupportActionBar(findViewById(toolbarId))
  supportActionBar?.run {
    action()
  }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
  beginTransaction().apply {
    action()
  }.commit()
}