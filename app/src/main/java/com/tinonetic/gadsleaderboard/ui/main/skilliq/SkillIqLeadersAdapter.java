package com.tinonetic.gadsleaderboard.ui.main.skilliq;

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
import com.tinonetic.gadsleaderboard.model.SkillIqLeader;

import java.util.ArrayList;

/*
* Adapter for Skill IQ leader RecyclerView
* */
public class SkillIqLeadersAdapter extends RecyclerView.Adapter<SkillIqLeadersAdapter.SkillIqLeadersViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<SkillIqLeader> mSkillIqLeaders;

    public SkillIqLeadersAdapter(Context context, ArrayList<SkillIqLeader> mSkillIqLeaders) {
        this.mContext = context;
        this.mSkillIqLeaders = mSkillIqLeaders;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SkillIqLeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate row layout
        View itemView = mLayoutInflater.inflate(R.layout.skilliq_leader_item_card,parent,false);
        return new SkillIqLeadersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqLeadersViewHolder holder, int position) {
        if(mSkillIqLeaders != null) {
            holder.bind(mSkillIqLeaders.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mSkillIqLeaders != null ? mSkillIqLeaders.size() : 0;
    }

    public class SkillIqLeadersViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextName;
        public final TextView mTextLeaderAchievementAndLocation;

        public SkillIqLeadersViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_learner_name);
            mTextLeaderAchievementAndLocation = (TextView) itemView.findViewById(R.id.text_achievement_and_location);
        }

        public void bind(SkillIqLeader skillIqLeader){

            // set the name
            mTextName.setText(skillIqLeader.getName());

            // set hours & country
            Resources res = mContext.getResources();
            String achievementAndLocation = res.getString(R.string.learning_leader_achievement_and_location,
                    skillIqLeader.getScore(),
                    skillIqLeader.getCountry());
            mTextLeaderAchievementAndLocation.setText(achievementAndLocation);
        }
    }
}
