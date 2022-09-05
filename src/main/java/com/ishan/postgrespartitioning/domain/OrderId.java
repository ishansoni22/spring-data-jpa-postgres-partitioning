package com.ishan.postgrespartitioning.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderId implements Serializable {

  public OrderId() {}

  //The application by default, works with active orders!
  public OrderId(String id) {
    this(id, ArchiveStatus.ACTIVE);
  }

  public OrderId(String id, ArchiveStatus archiveStatus) {
    this.id = id;
    this.archiveStatus = archiveStatus;
  }

  public enum ArchiveStatus {
    ACTIVE, ARCHIVED
  }

  private String id;

  @Enumerated(EnumType.STRING)
  private ArchiveStatus archiveStatus;

  public void markArchived() {
    this.archiveStatus = ArchiveStatus.ARCHIVED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderId orderId = (OrderId) o;
    return id.equals(orderId.id) &&
        archiveStatus == orderId.archiveStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, archiveStatus);
  }

}
