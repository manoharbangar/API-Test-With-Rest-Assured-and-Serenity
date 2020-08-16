Feature: To validate the comments for the post made by specific user
  Description: To perform the validations for the comments for the post made by a specific user

  Scenario Outline: To validate if emails in the comment section are in the proper format
    Given userId is obtained by searching username "<userName>"
    And list all the posts written by the user using userId
    When list all the comments for the posts made by user
    Then validate that email in the comment section is in the proper format

    Examples: 
      | userName |
      | Delphine |
      | Bret     |

  Scenario Outline: To validate if name in the comment is not empty
    Given userId is obtained by searching username "<userName>"
    And list all the posts written by the user using userId
    When list all the comments for the posts made by user
    Then validate that name in the comment section are not empty

    Examples: 
      | userName |
      | Bret     |
      | Delphine |

  Scenario Outline: To validate if body in the comment is not empty
    Given userId is obtained by searching username "<userName>"
    And list all the posts written by the user using userId
    When list all the comments for the posts made by user
    Then validate that text body in the comment section is not empty

    Examples: 
      | userName |
      | Delphine |
      | Bret     |

  Scenario Outline: To validate if length of body in the comment is not less than given characters
    Given userId is obtained by searching username "<userName>"
    And list all the posts written by the user using userId
    When list all the comments for the posts made by user
    Then validate that the length of text body in the comment is not less than "<commentLength>" characters

    Examples: 
      | userName | commentLength |
      | Bret     |           150 |
      | Delphine |           150 |
