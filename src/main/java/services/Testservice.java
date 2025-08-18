package services;

import Repository.TestRepository;
import entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Testservice {
    private final TestRepository testRepository;

    public Testservice(TestRepository testRepository){
        this.testRepository=testRepository;
    }

    public Page<Test> getAllTest(int page,int size){
        return testRepository.findAll(PageRequest.of(page, size));
    }

    public Mono<Test> save(Test test){
        return testRepository.save(test);
    }

    public Page<Test> getTestByCourseId(int page,int size,String courseId){
        return testRepository.findAllByCourseId(PageRequest.of(page,size),courseId);
    }

    public void deleteTest(Long testId){
        testRepository.deleteById(testId);
    }

}
