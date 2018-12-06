package com.fsoft.fa.interviewprocessmanagement.repository;

import com.fsoft.fa.interviewprocessmanagement.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    void deleteByRecruitment_Id(int id);

    /***
     * Find all candidates by RecruitmentId
     * @param recruitmentId - RecruitmentId
     * @return Candidates having above RecruitmentId
     */
    List<Candidate> findByRecruitment_Id(int recruitmentId);

    /***
     * Find all candidates by PositionId and RecruitmentId
     * @param positionId - PositionId
     * @param recruitmentId - RecruitmentId
     * @return Candidates having above PositionId and RecruitmentId
     */
    List<Candidate> findByPositions_IdAndRecruitment_Id(int positionId, int recruitmentId);

    /***
     * Find all candidates by SkillId and RecruitmentId
     * @param skillId - SkillId
     * @param recruitmentId - RecruitmentId
     * @return - Candidates having above SkillId and RecruitmentId
     */
    List<Candidate> findBySkills_IdAndRecruitment_Id(int skillId, int recruitmentId);

    List<Candidate> findByPotential(boolean potential);

    List<Candidate> findByPotentialAndRecruitment_Id(boolean potential, int recruitmentId);

    Optional<Candidate> findByName(String candidateName);

    Optional<Candidate> findOneByName(String candidateName);
}
