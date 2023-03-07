package jc.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Message {
	private String id;
	private String header;
	private String device;
	private String body;
}
