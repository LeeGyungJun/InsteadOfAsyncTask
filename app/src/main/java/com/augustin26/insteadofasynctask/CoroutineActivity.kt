package com.augustin26.insteadofasynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.augustin26.insteadofasynctask.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {

    lateinit var binding: ActivityCoroutineBinding
    // IO : disk 또는 네트워크 IO에 최적화
    // Default : CPU 연산이 필요하거나 JSON 파싱 List Sorting에 최적화
    // Main : UI와 상호 작용 하거나 간단한 작업에 최적화
    val mCoroutineScope: CoroutineScope = CoroutineScope(IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        binding.activity = this
    }

    fun btnOnClick(view: View) {
        when (view.id) {
            R.id.executeButton -> {
                BackgroundTask()
            }
            R.id.cancelButton -> {
                mCoroutineScope.cancel()
                binding.progress.progress = 0
            }
        }
    }

    fun BackgroundTask() {
        mCoroutineScope.launch {
            repeat(100) { i ->
                binding.progress.progress = i
                delay(100)
            }
            binding.progress.progress = 0
        }
    }
}