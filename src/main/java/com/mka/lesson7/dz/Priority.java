package com.mka.lesson7.dz;

public class Priority implements Comparable<Priority> {

    private Integer priority;
    private String name;

    public Priority(Integer priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Priority o) {
        if (this.priority > o.priority) {
            return 1;
        } else if (this.priority < o.priority) {
            return -1;
        }
        return this.name.compareTo(o.name);
    }
}
