package entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "errors")
public class Error {

  private Long id;
  private String message;

  public Error() { }

  public Error(String message) {
    this.message = message;
  }

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  @Type(type = "org.hibernate.type.TextType")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
