package com.data.values;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.data.bird.BirdObjectMother;
import com.data.bird.BirdRepository;

@AllArgsConstructor
@Component
public class SampleData {

    private BirdRepository birdRepository;

    void createSampleData() {
        birdRepository.save(BirdObjectMother.createCanary());
    }
}
