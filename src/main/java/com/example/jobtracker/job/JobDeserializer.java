package com.example.jobtracker.job;

import com.example.jobtracker.company.Company;
import com.example.jobtracker.company.CompanyRepository;
import com.example.jobtracker.company.CompanyService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
@Component
public class JobDeserializer extends JsonDeserializer<Job> {

    CompanyRepository companyRepository;
    @Autowired
    JobDeserializer(CompanyRepository repository){
        this.companyRepository = repository;
    }
    @Override
    public Job deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Optional<Company> companyOptional = companyRepository.findById(node.get("company").asLong());
        Job job = new Job();
        if (companyOptional.isPresent()){
            return new Job(
                    node.get("pay").asDouble(),
                    node.get("salary").asBoolean(),
                    node.get("description").asText(),
                    companyOptional.get(),
                    node.get("companyJobId").asText(),
                    node.get("position").asText()
            );
        }
        throw new IllegalArgumentException("Company with id: " + node.asText("company") + " does not exist");
    }
}
