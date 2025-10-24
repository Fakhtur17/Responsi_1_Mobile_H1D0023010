package com.example.responsi1mobileh1d023010

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023010.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023010.databinding.ActivityHalamanCoachBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HalamanCoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tampilkan dengan animasi 3 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.duration = 800
            binding.cardDeskripsi.startAnimation(fadeIn)
            binding.cardDeskripsi.visibility = android.view.View.VISIBLE
        }, 3000)

        fetchCoachData()
    }

    private fun fetchCoachData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val teamId = intent.getIntExtra("TEAM_ID", 404)
                val response = RetrofitInstance.api.getTeam(teamId)
                if (response.isSuccessful) {
                    val team = response.body()
                    val coach = team?.coach

                    // tampilkan nama dari API dulu
                    binding.tvCoachName.text = coach?.name ?: "Tidak diketahui"
                    binding.tvCoachBirth.text = coach?.dateOfBirth ?: "-"
                    binding.tvCoachCountry.text = coach?.nationality ?: "-"

                    // override manual jika API-nya salah
                    if (team?.id == 404) { // Wrexham
                        binding.tvCoachName.text = "Phil Parkinson"
                        binding.tvCoachBirth.text = "1967-12-01"
                        binding.tvCoachCountry.text = "England"
                        binding.imgCoach.setImageResource(R.drawable.nathan_jones)
                    }
                    // tambahkan override lain kalau mau untuk klub lain
                } else {
                    Toast.makeText(
                        this@HalamanCoachActivity,
                        "Gagal memuat data (${response.code()})",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@HalamanCoachActivity,
                    "Terjadi kesalahan: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
