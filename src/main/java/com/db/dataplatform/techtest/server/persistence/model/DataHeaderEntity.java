package com.db.dataplatform.techtest.server.persistence.model;

import com.db.dataplatform.techtest.server.persistence.BlockTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(
        name = "DATA_HEADER",
        uniqueConstraints = @UniqueConstraint(columnNames="NAME")
)
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class DataHeaderEntity {

    @Id
    @SequenceGenerator(name = "dataHeaderSequenceGenerator", sequenceName = "SEQ_DATA_HEADER", allocationSize = 1)
    @GeneratedValue(generator = "dataHeaderSequenceGenerator")
    @Column(name = "DATA_HEADER_ID")
    private Long dataHeaderId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BLOCKTYPE")
    @Enumerated(EnumType.STRING)
    private BlockTypeEnum blockType;


    @Column(name = "CREATED_TIMESTAMP")
    @EqualsAndHashCode.Exclude
    private Instant createdTimestamp;

    @Column(name = "DATA_CHECKSUM")
    private String dataChecksum;

    @PrePersist
    public void setTimestamps() {
        if (createdTimestamp == null) {
            createdTimestamp = Instant.now();
        }
    }

}
