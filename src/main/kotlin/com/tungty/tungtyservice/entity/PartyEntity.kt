package com.tungty.tungtyservice.entity

// Party Entity
@Entity
data class PartyEntity(
        @Id
        val partyId: String,
        val partyCode: String,
        val partyOwner: String,
        val partyName: String,
        val partyDescription: String,
        val partyType: String,
        val partyCategory: String,
        val appointmentDate: Date,
        val appointmentTime: String,
        val memberAmount: Int,
        @ElementCollection
        val memberList: List<String>,
        val createDateTime: Date,
        val updateDateTime: Date
)