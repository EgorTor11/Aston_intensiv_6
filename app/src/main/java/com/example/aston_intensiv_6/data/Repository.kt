package com.example.aston_intensiv_6.data

import com.github.javafaker.Faker

class Repository {
    companion object {
        fun getInstans(): Repository {
            return Repository()
        }
    }

    fun getContactList(): MutableList<Contact> {
        val contactList = mutableListOf<Contact>()
        val faker = Faker.instance()

        for (i in 0..101) {
            contactList.add(
                Contact(
                    i,
                    faker.phoneNumber().phoneNumber(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    "https://randomfox.ca//images//${1 + i}.jpg",
                    false
                )
            )


        }
        return contactList
    }
}