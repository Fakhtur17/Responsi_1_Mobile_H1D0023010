package com.example.responsi1mobileh1d023010.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023010.data.model.SquadMemberResponse
import com.example.responsi1mobileh1d023010.databinding.CardPlayerBinding
import com.example.responsi1mobileh1d023010.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class PlayerAdapter(private val players: List<SquadMemberResponse>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: CardPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = CardPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.binding.tvTitle.text = player.name
        holder.binding.tvAuthor.text = player.nationality

        val cardColor = when (player.position?.lowercase()) {
            "goalkeeper" -> Color.parseColor("#FFDE59")
            "defence", "centre-back", "left-back", "right-back" -> Color.parseColor("#598BFF")
            "midfield", "central midfield", "attacking midfield", "defensive midfield" -> Color.parseColor("#7DDA58")
            "offence", "centre-forward", "left winger" -> Color.parseColor("#E4080A")
            else -> Color.WHITE
        }
        holder.binding.cardView.setCardBackgroundColor(cardColor)

        holder.binding.cardView.setOnClickListener {
            val context = holder.itemView.context
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_player_detail, null)

            dialogView.findViewById<TextView>(R.id.tvDetailName).text = player.name
            dialogView.findViewById<TextView>(R.id.tvDetailBirth).text = player.dateOfBirth ?: "-"
            dialogView.findViewById<TextView>(R.id.tvDetailNationality).text = player.nationality ?: "-"
            dialogView.findViewById<TextView>(R.id.tvDetailPosition).text = player.position ?: "-"

            val bottomSheetDialog = BottomSheetDialog(context)
            bottomSheetDialog.setContentView(dialogView)
            bottomSheetDialog.show()
        }
    }

    override fun getItemCount() = players.size
}