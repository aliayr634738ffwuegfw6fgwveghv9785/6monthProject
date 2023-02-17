package com.example.a6monthproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.a6monthproject.base.BaseActivity
import com.example.a6monthproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }
}