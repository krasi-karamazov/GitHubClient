package kpk.dev.presentation.reposlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_repo.view.*
import kpk.dev.model.poko.Node
import kpk.dev.presentation.R

class ReposAdapter constructor(val listener: (String) -> Unit): RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    val reposData = mutableListOf<Node>()

    fun updateData(repos: List<Node>?) {
        if(repos != null) {
            reposData.addAll(repos)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder = RepoViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_repo, null, false))

    override fun getItemCount(): Int = reposData.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(reposData[position], listener)
    }


    class RepoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(node: Node, listener: (String) -> Unit) = with(view) {
            this.tv_repo_name.text = node.name
            this.tv_repo_url.text = node.url
            this.setOnClickListener{ listener(node.name) }
        }
    }
}