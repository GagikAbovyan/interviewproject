package homeworkapp.instigatemobile.com.interviewapplication.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;

import homeworkapp.instigatemobile.com.interviewapplication.R;
import homeworkapp.instigatemobile.com.interviewapplication.adapters.UsersAdapter;
import homeworkapp.instigatemobile.com.interviewapplication.models.Result;
import homeworkapp.instigatemobile.com.interviewapplication.models.UserRecyclers;
import homeworkapp.instigatemobile.com.interviewapplication.other.RandomApi;
import homeworkapp.instigatemobile.com.interviewapplication.providers.DataProvider;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersActivity extends AppCompatActivity {

    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startUsersList();
        getUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_users, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                usersAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                usersAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_search || super.onOptionsItemSelected(item);
    }

    private void swipeToDeleteLeft(final RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsersActivity.this);
                    builder.setMessage(getString(R.string.message_del));

                    builder.setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            usersAdapter.notifyItemRemoved(position);
                            DataProvider.getList().remove(position);
                        }
                    }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            usersAdapter.notifyItemRemoved(position + 1);
                            usersAdapter.notifyItemRangeChanged(position, usersAdapter.getItemCount());
                        }
                    }).show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void swipeToDeleteRight(final RecyclerView recyclerView) {
        final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                DataProvider.getList().remove(position);
                usersAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void startUsersList() {
        RecyclerView usersRecLists = findViewById(R.id.rec_view);
        usersAdapter = new UsersAdapter(this, DataProvider.getList());
        usersRecLists.setHasFixedSize(true);
        usersRecLists.setLayoutManager(new LinearLayoutManager(this));
        usersRecLists.setAdapter(usersAdapter);
        swipeToDeleteRight(usersRecLists);
        swipeToDeleteLeft(usersRecLists);
    }



    private void getUser() {
        RandomApi service = createService();
        service.randomUsers(1).enqueue(new retrofit2.Callback<UserRecyclers>() {
            @Override
            public void onResponse(retrofit2.Call<UserRecyclers> call, retrofit2.Response<UserRecyclers> response) {
                getResultsRandom(response);
            }
            @Override
            public void onFailure(retrofit2.Call<UserRecyclers> call, Throwable t) {
                Toast.makeText(UsersActivity.this, R.string.problem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RandomApi createService(){
        final String random = getString(R.string.random);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(random)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RandomApi.class);
    }

    private void getResultsRandom(final Response<UserRecyclers> response){
        final UserRecyclers userRecycler = response.body();
        final List<Result> result = userRecycler.getResults();
        DataProvider.getList().addAll(result);
        usersAdapter.notifyDataSetChanged();
    }
}
