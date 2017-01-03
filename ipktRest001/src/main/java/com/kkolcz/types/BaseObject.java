package com.kkolcz.types;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonRootName(value = "response")
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseObject {
}
