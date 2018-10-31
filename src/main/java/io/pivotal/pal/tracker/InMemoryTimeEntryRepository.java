package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> dataStore = new HashMap();
    private long idSequence = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry persisted = new TimeEntry(idSequence++,
                timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        long newId = persisted.getId();
        dataStore.put(newId, persisted);
        return find(newId);
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return dataStore.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return dataStore.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry persisted = new TimeEntry(timeEntryId,
                timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        long newId = persisted.getId();
        dataStore.put(newId, persisted);
        return find(newId);
    }

    @Override
    public void delete(long timeEntryId) {
        dataStore.remove(timeEntryId);

    }
}
