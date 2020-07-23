# LodestoneAssigment

#### Q4)Given your answer, what approaches do you recommend you need to take to improve your metrics, if the metric has not met engineering standards? 
Ans)

a)Give more training to the rater about the subject in question 

b)increase true positives by increasing dataset and having quality dataset and having quality dataset spread across all labels.

c)improve false neagtive.

#### Q5)Identify 3 more potential questions to consider that can be used to identify issues among raters.
Ans)

a)raters need to care about data and the success of your project, and a direct connection between rater team and engineer so engineer 
can iterate data features, attributes, and workflow based on what engineers are learning in the testing and validation phases of machine learning.

b)In data labeling, basic domain knowledge and contextual understanding is essential to create high quality, structured datasets for machine learning.

c)Give more training to the rater about the subject in question increase true positives by increasing dataset and having quality dataset .

#### Q6)Write a SQL query that outputs the following from the table:Agreement Rates for Each Rater on October 6

Ans)

select p.rater ,count(p.raterans3label) * 100.0 / (select count(*)  from  populated as c where p.rater = c.rater and date="2005-10-06" ) 
from populated as p where p.raterans3label = p.correctans3label and date="2005-10-06" group by p.rater;
