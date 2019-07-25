package com.bang.module.home.addsurvey.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bang.R;
import com.bang.base.ClickListener;
import com.bang.module.home.addsurvey.model.GetAllUserResponse;
import com.bang.module.home.survey.model.ContactModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ContactModel> contactList;
    private ArrayList<GetAllUserResponse.DataBean.UserListBean> userListBeans;
    private List<ContactModel> globlemContactList;
    private ClickListener.ContactNumberClick clickListener;
    private int selected_position = -1;


    public void filter(String charText) {
        charText = charText.toLowerCase();
        contactList.clear();
        if (charText.length() == 0) {
            contactList.addAll(globlemContactList);
        } else {
            for (int i = 0; i < globlemContactList.size(); i++) {
                if (globlemContactList.get(i).name.toLowerCase().startsWith(charText)) {
                    contactList.add(globlemContactList.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    public ContactListAdapter(ClickListener.ContactNumberClick clickListener, ArrayList<ContactModel> contactList, Context mContext, ArrayList<GetAllUserResponse.DataBean.UserListBean> userListBeans) {
        this.contactList = contactList;
        this.mContext = mContext;
        this.clickListener = clickListener;
        this.globlemContactList = new ArrayList<>();
        this.userListBeans = userListBeans;
        if (contactList != null) {
            globlemContactList.addAll(contactList);
        }

        for (int k= 0; k<contactList.size(); k++){
            contactList.get(k).sort = 123;
        }

        for (int j = 0; j < contactList.size(); j++) {
            for (int i = 0; i < userListBeans.size(); i++) {
                if (userListBeans.get(i).getPhone_number().equals(contactList.get(j).mobileNumber)) {
                    contactList.get(j).sort = 0;
                    break;
                }
            }
        }
        Collections.sort(contactList,new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel o1, ContactModel o2) {
                return o2.sort.compareTo(o1.sort);
            }
        });

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_view, viewGroup, false);
        return new ContactListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.tvContactName.setText(contactList.get(i).name);
        Glide.with(mContext).load(contactList.get(i).photo).error(R.drawable.user_img).into(viewHolder.ivContact);
        viewHolder.rbSelectContact.setVisibility(View.GONE);
        viewHolder.tvInvite.setVisibility(View.VISIBLE);
        for (int j = 0; j < userListBeans.size(); j++) {
            if (userListBeans.get(j).getPhone_number().equals(contactList.get(i).mobileNumber)) {
                viewHolder.rbSelectContact.setVisibility(View.VISIBLE);
                viewHolder.tvInvite.setVisibility(View.GONE);
                break;
            }
        }

        if (i == selected_position) {
            viewHolder.rbSelectContact.setChecked(true);
        } else {
            viewHolder.rbSelectContact.setChecked(false);
        }

        viewHolder.rlContactListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != -1) {
                    notifyItemChanged(selected_position);
                    selected_position = i;
                    notifyItemChanged(selected_position);
                    if (viewHolder.tvInvite.getVisibility() == View.VISIBLE) {

                    } else {

                        clickListener.onContactClick(contactList.get(i).mobileNumber);
                    }

                }
            }
        });

        viewHolder.tvInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bang App");
                txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Invited for the Bang App");
                mContext.startActivity(Intent.createChooser(txtIntent, "Share"));
            }
        });

    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivContact;
        private TextView tvContactName;
        private TextView tvInvite;
        private CheckBox rbSelectContact;
        private RelativeLayout rlContactListener;

        ViewHolder(View itemView) {
            super(itemView);
            ivContact = itemView.findViewById(R.id.ivContact);
            tvContactName = itemView.findViewById(R.id.tvContactName);
            tvInvite = itemView.findViewById(R.id.tvInvite);
            rbSelectContact = itemView.findViewById(R.id.rbSelectContact);
            rlContactListener = itemView.findViewById(R.id.rlContactListener);
        }
    }
}
