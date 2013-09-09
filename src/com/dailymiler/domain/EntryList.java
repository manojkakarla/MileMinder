package com.dailymiler.domain;

import java.util.Arrays;
import java.util.List;

public class EntryList {

  private Entry[] entries;

    public Entry[] getEntries() {
        return entries;
    }
    public List<Entry> getEntryList() {
        return Arrays.asList(entries);
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }
}
