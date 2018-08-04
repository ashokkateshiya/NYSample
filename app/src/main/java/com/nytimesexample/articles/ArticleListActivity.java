package com.nytimesexample.articles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nytimesexample.DateFormatter;
import com.nytimesexample.MyApplication;
import com.nytimesexample.R;
import com.nytimesexample.data.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends AppCompatActivity implements ArticleContract.View , SwipeRefreshLayout.OnRefreshListener{

    private boolean mTwoPane;
    private ArticlePresenter mPresenter;
    private SimpleItemRecyclerViewAdapter mAdapter;
    private boolean isActive;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        isActive = true;

        mPresenter = new ArticlePresenter(MyApplication.getArticleRepo(), this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        View recyclerView = findViewById(R.id.item_list);
        setupRecyclerView((RecyclerView) recyclerView);

        mPresenter.start();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter = new SimpleItemRecyclerViewAdapter(this, new ArrayList<Article>(), mTwoPane));
    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {

    }

    @Override
    public void showLoading(boolean active) {
        mSwipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showArticles(List<Article> articles) {
        mAdapter.replaceData(articles);
    }

    @Override
    public void showNoArticles() {
        Toast.makeText(getApplicationContext(), "No articles available.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingArticleError() {
        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void onRefresh() {
        mPresenter.start();
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ArticleListActivity mParentActivity;
        private final List<Article> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article item = (Article) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable("article", item);
                    ArticleDetailFragment fragment = new ArticleDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ArticleDetailActivity.class);
                    intent.putExtra("article", item);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ArticleListActivity parent,
                                      List<Article> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        void replaceData(List<Article> articles) {
            mValues.clear();
            mValues.addAll(articles);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tvTitle.setText(mValues.get(position).getTitle());
            holder.tvBy.setText(mValues.get(position).getByline());
            holder.tvPublishedDate.setText(DateFormatter.convertDateFormat(mValues.get(position).getPublishedDate()
            , DateFormatter.DATE_FORMAT_YYYY_MM_DD, DateFormatter.DATE_FORMAT_DD_MMM_YYYY));
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView tvTitle, tvBy, tvPublishedDate;

            ViewHolder(View view) {
                super(view);
                tvTitle = view.findViewById(R.id.tvTitle);
                tvBy = view.findViewById(R.id.tvBy);
                tvPublishedDate = view.findViewById(R.id.tvPublishDate);
            }
        }
    }
}
