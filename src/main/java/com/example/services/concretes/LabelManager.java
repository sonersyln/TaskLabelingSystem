package com.example.services.concretes;

import com.example.core.exceptions.NotFoundException;
import com.example.core.utilities.constants.MessageConstants;
import com.example.core.utilities.mappers.ModelMapperService;
import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.core.utilities.results.SuccessDataResult;
import com.example.core.utilities.results.SuccessResult;
import com.example.models.Label;
import com.example.repositories.LabelRepository;
import com.example.services.abstracts.LabelService;
import com.example.services.dtos.requests.AddLabelRequest;
import com.example.services.dtos.requests.UpdateLabelRequest;
import com.example.services.dtos.responses.GetLabelResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LabelManager implements LabelService {
    private final LabelRepository labelRepository;
    private final ModelMapperService mapperService;

    @Override
    public DataResult<List<GetLabelResponse>> getAllLabels() {
        List<Label> colors = this.labelRepository.findAll();
        List<GetLabelResponse> responses = colors.stream().map(color -> this.mapperService
                        .forResponse()
                        .map(color, GetLabelResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetLabelResponse> getById(int id) {

        Label label = this.labelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.LABEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetLabelResponse responses = this.mapperService.forResponse().map(label, GetLabelResponse.class);

        return new SuccessDataResult<>(responses, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addLabel(AddLabelRequest addLabelRequest) {

        Label label = this.mapperService.forRequest().map(addLabelRequest, Label.class);

        this.labelRepository.save(label);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateLabel(UpdateLabelRequest updateLabelRequest) {
        this.labelRepository.findById(updateLabelRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException(MessageConstants.LABEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        Label label = this.mapperService.forRequest().map(updateLabelRequest, Label.class);
        this.labelRepository.save(label);
        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteLabel(int id) {
       Label label = this.labelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageConstants.LABEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

       this.labelRepository.delete(label);
         return new SuccessResult(MessageConstants.DELETE.getMessage());
    }
}
