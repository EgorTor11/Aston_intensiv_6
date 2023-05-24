package com.example.aston_intensiv_6.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aston_intensiv_6.R
import com.example.aston_intensiv_6.data.Contact
import com.example.aston_intensiv_6.databinding.ItemContactBinding

class ContactsAdapter(
    private val listener: Listener,
) : ListAdapter<Contact, ContactsAdapter.NoteHolder>(ItemCallback), View.OnClickListener,
    View.OnLongClickListener {
    override fun onClick(v: View) {
        val contact = v.tag as Contact
        when (v.id) {
            R.id.imDelete -> {
                listener.onImDeleteClick(contact.copy())
            }
            else->listener.onRootClick(contact.copy())
        }
    }

    override fun onLongClick(v: View?): Boolean {
        val contact = v?.tag as Contact
        listener.onRootLongClick(contact.copy())
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        binding.root.setOnLongClickListener(this)
        binding.root.setOnClickListener(this)
        binding.imDelete.setOnClickListener(this)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val contact = getItem(position)
        with(holder.binding) {
            root.tag=contact
            tvName.tag = contact
            tvNumber.tag = contact
            imDelete.tag = contact
            tvId.tag=contact
            tvId.text=contact.id.toString()
            tvName.text = "${contact.firstName} ${contact.lastName}"
            tvNumber.text = contact.phoneNumber
            Glide.with(root.context)
                .load(contact.imageUrl)
                .circleCrop()
                .placeholder(android.R.drawable.ic_menu_myplaces)
                .into(imageView)
            if (contact.isImDeleteVisible) {
                imDelete.visibility = View.VISIBLE
            } else {
                imDelete.visibility = View.GONE
            }
        }
    }

    interface Listener {
        fun onImDeleteClick(contact: Contact)
        fun onRootLongClick(contact: Contact)
        fun onRootClick(contact: Contact)
    }

    class NoteHolder(
        val binding: ItemContactBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}
