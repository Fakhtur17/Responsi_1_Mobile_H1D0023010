package com.example.responsi1mobileh1d023010.data.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("crest") val crest: String?,
    @SerializedName("coach") val coach: CoachResponse,
    @SerializedName("squad") val squad: List<SquadMemberResponse>
)

data class CoachResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @SerializedName("nationality") val nationality: String?
)

data class SquadMemberResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @SerializedName("nationality") val nationality: String?
)
