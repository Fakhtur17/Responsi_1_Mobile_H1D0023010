package com.example.responsi1mobileh1d023010

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.responsi1mobileh1d023010.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023010.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val defaultTeamId = 348

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMenus()
        loadTeamData(defaultTeamId)
    }

    private fun setupMenus() {
        // Set ikon dan teks menu (pastikan gambar drawable-nya ada)
        binding.layoutClub.imgIcon.setImageResource(R.drawable.ic_ball)
        binding.layoutClub.tvLayout.text = "Detail Club"

        binding.layoutCoach.imgIcon.setImageResource(R.drawable.ic_coach)
        binding.layoutCoach.tvLayout.text = "Coach"

        binding.layoutTeam.imgIcon.setImageResource(R.drawable.ic_team)
        binding.layoutTeam.tvLayout.text = "Team / Squad"

        // Navigasi ke halaman lain
        binding.layoutClub.root.setOnClickListener {
            startActivity(
                Intent(this, HalamanClubActivity::class.java)
                    .putExtra("TEAM_ID", defaultTeamId)
            )
        }

        binding.layoutCoach.root.setOnClickListener {
            startActivity(
                Intent(this, HalamanCoachActivity::class.java)
                    .putExtra("TEAM_ID", defaultTeamId)
            )
        }

        binding.layoutTeam.root.setOnClickListener {
            startActivity(
                Intent(this, HalamanTeamActivity::class.java)
                    .putExtra("TEAM_ID", defaultTeamId)
            )
        }
    }

    private fun loadTeamData(teamId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getTeam(teamId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val team = response.body()

                        // tampilkan nama klub
                        binding.tvNamaClub.text = team?.name ?: "Unknown Team"

                        // tampilkan logo klub (crest)
                        Glide.with(this@MainActivity)
                            .load(team?.crest)
                            .placeholder(android.R.drawable.ic_menu_gallery) // gambar loading bawaan
                            .error(android.R.drawable.ic_menu_report_image)  // gambar error bawaan
                            .into(binding.ivLogo)

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Gagal memuat data (${response.code()})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Terjadi kesalahan: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}