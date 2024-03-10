package br.com.sas.travel.criteria.model;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ActivityCriteria {

	private List<ActivityType> activityPreferences;

	private ActivityDifficulty difficultyLevel;

}
