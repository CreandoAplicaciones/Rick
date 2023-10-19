package com.rick.and.morty.ui.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rick.and.morty.R
import com.rick.and.morty.databinding.ItemListBinding
import com.rick.and.morty.domain.model.AnimationCharacter


class DetailAdapter(private val characters: List<AnimationCharacter>) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(characters[position]) }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: AnimationCharacter) = with(binding) {
            txtName.text = character.name
            txtGender.text = character.gender
            txtSpecies.text = character.species
            txtStatus.text = character.status

            when (character.gender) {
                "Female" -> imgGender.setImageResource(R.drawable.icon_female)
                "Male" -> imgGender.setImageResource(R.drawable.icon_male)
                else -> imgGender.setImageResource(R.drawable.icon_unknown)
            }

            when (character.status) {
                "Alive" -> imgStatus.setImageResource(R.drawable.icon_alive)
                "Dead" -> imgStatus.setImageResource(R.drawable.icon_dead)
                else -> imgStatus.setImageResource(R.drawable.icon_unknown)
            }

            when (character.species) {
                "Human" -> imgSpecies.setImageResource(R.drawable.icon_human)
                "Alien" -> imgSpecies.setImageResource(R.drawable.icon_alien)
                else -> imgSpecies.setImageResource(R.drawable.icon_unknown)
            }


            Glide.with(imgIcon.context)
                .load(character.image)
                .centerCrop()
                .into(imgIcon)

        }
    }
}