package homeworkapp.instigatemobile.com.interviewapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import homeworkapp.instigatemobile.com.interviewapplication.R;
import homeworkapp.instigatemobile.com.interviewapplication.activities.InfoActivity;
import homeworkapp.instigatemobile.com.interviewapplication.activities.UsersActivity;
import homeworkapp.instigatemobile.com.interviewapplication.models.Result;
import homeworkapp.instigatemobile.com.interviewapplication.models.User;
import homeworkapp.instigatemobile.com.interviewapplication.models.UserRecyclers;
import homeworkapp.instigatemobile.com.interviewapplication.providers.DataProvider;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> implements Filterable{

    private final Context context;
    private final List<Result> list;
    private List<Result> listSearch;

    public UsersAdapter(final Context context, final List<Result> list) {
        this.context = context;
        this.list = list;
        listSearch = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final Result result = list.get(position);
        Picasso.get().load(result.getPicture().getMedium()).into(holder.image);
        holder.nameText.setText(result.getName().getFirst());
        holder.titleText.setText(result.getName().getTitle());
        openInfo(position, holder);
        callButton(holder, result);
        emailButton(holder, result);
    }

    private void emailButton(final UserViewHolder holder, final Result result) {
        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + result.getEmail()));
                context.startActivity(intent);
            }
        });
    }

    private void callButton(final UserViewHolder holder, final Result result) {
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + result.getPhone()));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listSearch.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchText = String.valueOf(charSequence);
                if (searchText.isEmpty()) {
                    listSearch = list;
                } else {
                    List<Result> newList = new ArrayList<>();
                    for (final Result results: list) {
                        if (results.getName().getFirst().contains(searchText)) {
                            newList.add(results);
                        }
                    }
                    listSearch = newList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listSearch;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listSearch = (List<Result>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView image;
        private TextView nameText;
        private TextView titleText;
        private ImageButton callButton;
        private ImageButton emailButton;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image_item);
            nameText = itemView.findViewById(R.id.name_item);
            titleText = itemView.findViewById(R.id.title_item);
            callButton = itemView.findViewById(R.id.call_button);
            emailButton = itemView.findViewById(R.id.email_button);
        }
    }


    private void openInfo(final int position, final UserViewHolder holder){
        final Intent intent = new Intent(context, InfoActivity.class);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(DataProvider.getMyKey(), position);
                context.startActivity(intent);
            }
        });
    }
}
