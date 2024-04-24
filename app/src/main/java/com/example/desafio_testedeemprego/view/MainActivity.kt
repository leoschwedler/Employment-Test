package com.example.desafio_testedeemprego.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_testedeemprego.Adapter.MainAdapter
import com.example.desafio_testedeemprego.api.RetrofitHelper
import com.example.desafio_testedeemprego.databinding.ActivityMainBinding
import com.example.desafio_testedeemprego.model.ResponseAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var mainAdapter: MainAdapter

    private val imgurAPI by lazy {
        RetrofitHelper.imgurAPI
    }
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainAdapter = MainAdapter()
        binding.rvMain.adapter = mainAdapter
        binding.rvMain.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
    }

    override fun onStart() {
        super.onStart()
        recoverImageApi()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    fun recoverImageApi() {
        job = CoroutineScope(Dispatchers.IO).launch {
            var response: Response<ResponseAPI>? = null
            try {
                response = imgurAPI.searchImg("cats")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (response != null && response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    val list = result.data
                    val listImg = mutableListOf<String>()
                    list.forEach {
                        if (it.images != null && it.images.isNotEmpty()) {
                            val image = it.images[0]
                            val type = image.type
                            if (type == "image/jpeg") {
                                listImg.add(image.link)
                            }
                        }
                    }
                    withContext(Dispatchers.Main) {
                        mainAdapter.addList(listImg)
                    }
                }
            } else {
                Log.i("teste", "Erro ao recuperar as imagens")
            }

        }
    }
}
