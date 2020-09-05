package com.example.data.response;

import com.example.data.model.Reminder;
import com.example.data.model.Response;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
public class ReminderResponse extends Response {

    @SerializedName("data_list") public List<Reminder> reminderList;
}
