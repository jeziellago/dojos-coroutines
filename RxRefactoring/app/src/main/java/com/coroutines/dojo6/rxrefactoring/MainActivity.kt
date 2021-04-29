package com.coroutines.dojo6.rxrefactoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coroutines.dojo6.rxrefactoring.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()

        // Completable
        completableExample(disposables, ::result)

        // Single
        singleExample(disposables, ::result)

        // Observable
        observableExample(disposables, ::result)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    private fun result(from: String, result: String, value: Any? = null) {
        val newText = binding.txtResult.text.toString() + "\n\n" +
                "[$from] $result->${value?.toString().orEmpty()}"
        binding.txtResult.text = newText
    }

    private fun setupView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}