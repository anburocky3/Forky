package cdn.retrofito.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cdn.retrofito.R;
import cdn.retrofito.model.Repos;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<Repos> reposList;
    private Context context;

    public RepoAdapter(List<Repos> reposList, Context context) {
        this.reposList = reposList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Repos repos = reposList.get(position);

        holder.heading.setText(repos.getName());
        holder.desc.setText(repos.getDescription());

        holder.repoListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked: " +  repos.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView heading, desc;
        private LinearLayout repoListContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.heading);
            desc = itemView.findViewById(R.id.desc);
            repoListContainer = itemView.findViewById(R.id.repoListContainer);
        }
    }
}
