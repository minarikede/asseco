package com.application.asseco.criteria;

import com.application.asseco.filter.LongFilter;
import com.application.asseco.filter.StringFilter;
import lombok.Data;

@Data
public class UserCriteria {
    private LongFilter id;

    private StringFilter address;

    private StringFilter email;

    private StringFilter name;

    private StringFilter phone;

    private StringFilter userName;
}
