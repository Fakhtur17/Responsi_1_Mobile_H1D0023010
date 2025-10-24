package com.example.responsi1mobileh1d023010

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023010.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023010.databinding.ActivityHalamanClubBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HalamanClubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanClubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil TEAM_ID dari Intent (jika tidak ada, pakai 66 = Manchester United)
        val teamId = intent.getIntExtra("TEAM_ID", 348)

        loadTeamName(teamId)
    }

    private fun loadTeamName(teamId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val res = RetrofitInstance.api.getTeam(teamId)
                if (res.isSuccessful) {
                    val team = res.body()
                    // set nama klub dari API ke TextView
                    binding.tvWelcome.text = team?.name ?: getString(R.string.nama_club)
                    // kalau mau, isi deskripsi singkat default kalau kosong
                    if (binding.tvDescription.text.isNullOrBlank()) {
                        binding.tvDescription.text =
                            "Informasi ${team?.name ?: "klub"} diambil dari Football-Data API."
                    }
                } else {
                    Toast.makeText(
                        this@HalamanClubActivity,
                        "Gagal memuat data (${res.code()})",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@HalamanClubActivity,
                    "Terjadi kesalahan: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
