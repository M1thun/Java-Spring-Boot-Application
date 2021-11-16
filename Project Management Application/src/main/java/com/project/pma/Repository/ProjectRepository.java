package com.project.pma.Repository;

import com.project.pma.Entities.Project;
import com.project.pma.dto.ProjectStatus;
import com.project.pma.dto.TimeChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {
    @Override
    public List<Project> findAll();

    @Query(nativeQuery = true, value="select stage as stageName, count(project_id) as projectCount from project " +
            "group by stage")
    public List<ProjectStatus> projectStatus();

    @Query(nativeQuery = true,value="SELECT NAME AS name,START_DATE as startDate,END_DATE as endDate FROM PROJECT")
    public List<TimeChartData> getTimeData();

}
