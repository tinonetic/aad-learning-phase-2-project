package com.tinonetic.gadsleaderboard.ui.main.learning;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinonetic.gadsleaderboard.R;
import com.tinonetic.gadsleaderboard.model.LearningLeader;

import java.util.ArrayList;

/*
* Adapter for leaarning leader RecyclerView
* */
public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.LearningLeadersViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<LearningLeader> mLearningLeaders;

    public LearningLeadersAdapter(Context context, ArrayList<LearningLeader> mLearningLeaders) {
        this.mContext = context;
        this.mLearningLeaders = mLearningLeaders;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LearningLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate row layout
        View itemView = mLayoutInflater.inflate(R.layout.learning_leader_item,parent,false);
        return new LearningLeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersViewHolder holder, int position) {
        if(mLearningLeaders != null) {
            holder.bind(mLearningLeaders.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders != null ? mLearningLeaders.size() : 0;
    }

    public class LearningLeadersViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextName;
        public final TextView mTextLeaderAchievementAndLocation;

        public LearningLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_learner_name);
            mTextLeaderAchievementAndLocation = (TextView) itemView.findViewById(R.id.text_achievement_and_location);
        }

        public void bind(LearningLeader learningLeader){

            // set the name
            mTextName.setText(learningLeader.getName());

            // set hours & country
            Resources res = mContext.getResources();
            String achievementAndLocation = res.getString(R.string.learning_leader_achievement_and_location,
                    learningLeader.getHours(),
                    learningLeader.getCountry());
            mTextLeaderAchievementAndLocation.setText(achievementAndLocation);
        }
    }
}
