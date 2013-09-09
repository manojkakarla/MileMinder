package com.dailymiler.domain;

import java.util.Arrays;
import java.util.List;

public class FriendsList {

  private User[] friends;

    public User[] getFriends() {
        return friends;
    }
    public List<User> getFriendsList() {
        return Arrays.asList(friends);
    }

    public void setFriends(User[] friends) {
        this.friends = friends;
    }
}
