package ru.pnzgu.fvt.moipvm.vi19.br2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Activity;
import ru.pnzgu.fvt.moipvm.vi19.br2.repositories.ActivityRepository;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }
}
