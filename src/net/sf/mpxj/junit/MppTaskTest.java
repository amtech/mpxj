/*
 * file:       MppTaskTest.java
 * author:     Wade Golden
 * copyright:  (c) Tapster Rock Limited 2006
 * date:       22-August-2006
 */
 
/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package net.sf.mpxj.junit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.mpxj.AccrueType;
import net.sf.mpxj.ConstraintType;
import net.sf.mpxj.Duration;
import net.sf.mpxj.Priority;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.ProjectHeader;
import net.sf.mpxj.Relation;
import net.sf.mpxj.RelationType;
import net.sf.mpxj.Task;
import net.sf.mpxj.TaskType;
import net.sf.mpxj.TimeUnit;
import net.sf.mpxj.mpd.MPDDatabaseReader;
import net.sf.mpxj.mpp.MPPReader;

/**
 * Testsb to exercise MPP file read functionlaity for various versions of
 * MPP file.
 */
public class MppTaskTest extends MPXJTestCase 
{
   
   /**
    * Test task data read from an MPP9 file.
    * 
    * @throws Exception
    */   
    public void testMpp9Task() 
       throws Exception 
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp9task.mpp");
        testBasicTask(mpp);
    }

    /**
     * Test task data read from an MPP12 file.
     * 
     * @throws Exception
     */       
    public void testMpp12Task()
       throws Exception
    {
       ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp12task.mpp");
       testBasicTask(mpp);
    }

    /**
     * Test task data read from an MPD9 file.
     * 
     * @throws Exception
     */       
    public void testMpd9Task ()
       throws Exception
    {
       ProjectFile mpp = new MPDDatabaseReader().read (m_basedir + "/mpp9task.mpd");
       testBasicTask(mpp);       
    }
    
    /**
     * Test task data read from an MPP9 file.
     * 
     * @throws Exception
     */   
    public void testMpp9Baseline()
       throws Exception
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp9baseline.mpp");
        testBaselineTasks(mpp);
    }

    /**
     * Test task data read from an MPP12 file.
     * 
     * @throws Exception
     */   
    public void testMpp12Baseline()
       throws Exception
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp12baseline.mpp");
        testBaselineTasks(mpp);
    }

    /**
     * Test task data read from an MPD9 file.
     * 
     * @throws Exception
     */   
   public void testMpd9Baseline ()
      throws Exception
   {
      ProjectFile mpp = new MPDDatabaseReader().read (m_basedir + "/mpp9baseline.mpd");
      testBaselineTasks(mpp);       
   }

    /**
     * Test Split Tasks in an MPP9 file.
     *
     * @throws Exception
     */
    public void testMpp9Splits()
       throws Exception 
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp9splittask.mpp");
        testSplitTasks(mpp);
    }

    /**
     * Test Split Tasks in an MPP12 file.
     *
     * @throws Exception
     */
    public void testMpp12Splits()
       throws Exception 
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp12splittask.mpp");
        testSplitTasks(mpp);
    }

    /**
     * Test Split Tasks in an MPD9 file.
     * 
     * Currently split tasks are not supported in MPD files.
     *
     * @throws Exception
     */
    public void testMpd9Splits()
       throws Exception 
    {
//       ProjectFile mpp = new MPDDatabaseReader().read (m_basedir + "/mpp9splittask.mpd");
//       testSplitTasks(mpp);
    }

    /**
     * Tests Relations in an MPP9 file.
     *
     * @throws Exception
     */
    public void testMpp9Relations()
       throws Exception 
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp9relations.mpp");
        testRelations(mpp);
    }

    /**
     * Tests Relations in an MPD9 file.
     *
     * @throws Exception
     */
    public void testMpd9Relations()
       throws Exception 
    {
       ProjectFile mpp = new MPDDatabaseReader().read (m_basedir + "/mpp9relations.mpd");
       testRelations(mpp);
    }

    /**
     * Tests Relations in an MPP12 file.
     *
     * @throws Exception
     */
    public void testMpp12Relations()
       throws Exception 
    {
        ProjectFile mpp = new MPPReader().read (m_basedir + "/mpp12relations.mpp");
        testRelations(mpp);
    }

      /**
       * Tests dozens of basic fields of a Task.
       * @param mpp The ProjectFile being tested.
       * @throws Exception
       *
       * <br><br>
       * Columns not tested:<br><br>
       *
       * Overtime Cost, Remaining Overtime Cost, Actual Overtime Cost<br>
       * Overtime Work, Remaining Overtime Work, Actual Overtime Work<br>
       * Actual Start<br>
       * Actual Finish<br>
       * Baseline Cost<br>
       * Baseline Start<br>
       * Baseline Finish<br>
       * Baseline Duration<br>
       * Baseline Work<br>
       * Confirmed (??? - don't know how to make this field 'Yes' in Project)<br>
       * Cost Rate Table (calculated in Project)<br>
       * Critical (Calculated in Steelray)<br>
       * Estimated (test in another method)<br>
       * External Task<br>
       * Group By Summary<br>
       * Hyperlink Subaddress<br>
       * Linked Fields<br>
       * Outline Code1-10<br>
       * Objects<br>
       * Overallocated<br>
       * Preleveled Start<br>
       * Preleveled Finish<br>
       * Recurring<br>
       * Resource Phonetics<br>
       * Resource Type<br>
       * Response Pending<br>
       * Subproject File<br>
       * Subproject Read Only<br>
       * Predecessors<br>
       * Summary<br>
       * Task Calendar<br>
       * Team Status Pending<br>
       * Type<br>
       * Unique ID Predecessors, Unique ID Succeessors<br>
       * Update Needed<br>
       * WBS Predecessors, WBS Succeessors<br>
       * Work Contour<br><br><br>
       *
       * Fields that are not supported in the MPP9 format (usually return null or false)<br><br>
       *
       * AWCP<br>
       * BCWP<br>
       * BCWS<br>
       * CV<br>
       * Hyperlink Href<br>
       * Project<br>
       * Regular Work<br>
       * Resource Group<br>
       * Resource Initials<br>
       * Resource Names<br>
       * Successors<br>
       * SV<br>
       * VAC<br>
       *
       */      
      public void testBasicTask(ProjectFile mpp) 
         throws Exception 
      {

         DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");

         ProjectHeader projectHeader = mpp.getProjectHeader();
         assertNotNull(projectHeader);

         // test various global properties
         List listAllTasks = mpp.getAllTasks();
         List listAllResources = mpp.getAllResources();
         assertTrue(listAllTasks != null);
         assertTrue(listAllResources != null);
         assertEquals(2, listAllTasks.size());
         // This will fail in MPP12 as we appear to have a summary resource
         //assertEquals(1, listAllResources.size());

         /* Test Specifics */

         // task 0 - the project task
         Task task = (Task)listAllTasks.get(0);
         assertTrue(task != null);
         assertEquals(0, task.getID().intValue());
         assertEquals("MPP12 Test", task.getName());

         // task 1
         task = (Task)listAllTasks.get(1);
         assertTrue(task != null);
         assertEquals(1, task.getID().intValue());
         // name
         assertEquals("Task #1", task.getName());
         // start and finish
         final String expectedStart = "23/08/2006";
         final String expectedFinish = "29/08/2006";
         assertEquals(expectedStart, df.format(task.getStart()));
         assertEquals(expectedFinish, df.format(task.getFinish()));
         // no predecessors
         assertTrue(task.getPredecessors() == null);
         // duration
         Duration expectedDuration = Duration.getInstance(1, TimeUnit.WEEKS);
         // note that this is a direct comparison - TimeUnit must match
         assertEquals(expectedDuration, task.getDuration());
         // work
         expectedDuration = Duration.getInstance(40, TimeUnit.HOURS);
         assertEquals(expectedDuration, task.getWork());
         // percent complete
         Number expectedPctComp = new Double(45);
         assertEquals(expectedPctComp,  task.getPercentageComplete());
         // percent work complete
         expectedPctComp = new Double(45);
         assertEquals(expectedPctComp, task.getPercentageWorkComplete());
         // cost
         Number expectedCost = new Double(5000);
         assertEquals(expectedCost, task.getCost());
         // actual cost
         expectedCost = new Double(2800);
         assertEquals(expectedCost, task.getActualCost());
         // fixed cost
         expectedCost = new Double(1000);
         assertEquals(expectedCost, task.getFixedCost());
         // remaining cost
         expectedCost = new Double(2200);
         assertEquals(expectedCost, task.getRemainingCost());
         // actual work
         expectedDuration = Duration.getInstance(18, TimeUnit.HOURS);
         assertEquals(expectedDuration, expectedDuration);
         // contact
         String expectedContact = "wade";
         assertEquals(expectedContact, task.getContact());
         // constraint date
         assertEquals(expectedStart, df.format(task.getConstraintDate()));
         // constraint type
         assertEquals(ConstraintType.MUST_START_ON, task.getConstraintType());
         // custom cost columns
         expectedCost = new Double(1);
         assertEquals(expectedCost, task.getCost1());
         expectedCost = new Double(2);
         assertEquals(expectedCost, task.getCost2());
         expectedCost = new Double(3);
         assertEquals(expectedCost, task.getCost3());
         expectedCost = new Double(4);
         assertEquals(expectedCost, task.getCost4());
         expectedCost = new Double(5);
         assertEquals(expectedCost, task.getCost5());
         expectedCost = new Double(6);
         assertEquals(expectedCost, task.getCost6());
         expectedCost = new Double(7);
         assertEquals(expectedCost, task.getCost7());
         expectedCost = new Double(8);
         assertEquals(expectedCost, task.getCost8());
         expectedCost = new Double(9);
         assertEquals(expectedCost, task.getCost9());
         expectedCost = new Double(10);
         assertEquals(expectedCost, task.getCost10());
         // cost variance
         expectedCost = new Double(5000);
         assertEquals(expectedCost, task.getCostVariance());
         // created
         //Date dateExpected = new Date(1156360320000L);
         assertEquals(expectedStart, df.format(task.getCreateDate()));
         // custom date columns
         assertEquals("25/08/2006", df.format(task.getDate1()));
         assertEquals("26/08/2006", df.format(task.getDate2()));
         assertEquals("27/08/2006", df.format(task.getDate3()));
         assertEquals("28/08/2006", df.format(task.getDate4()));
         assertEquals("29/08/2006", df.format(task.getDate5()));
         assertEquals("30/08/2006", df.format(task.getDate6()));
         assertEquals("31/08/2006", df.format(task.getDate7()));
         assertEquals("01/09/2006", df.format(task.getDate8()));
         assertEquals("02/09/2006", df.format(task.getDate9()));
         assertEquals("03/09/2006", df.format(task.getDate10()));
         // deadline
         assertEquals("30/08/2006", df.format(task.getDeadline()));
         // custom duration columns
         expectedDuration = Duration.getInstance(1, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration1());
         expectedDuration = Duration.getInstance(2, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration2());
         expectedDuration = Duration.getInstance(3, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration3());
         expectedDuration = Duration.getInstance(4, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration4());
         expectedDuration = Duration.getInstance(5, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration5());
         expectedDuration = Duration.getInstance(6, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration6());
         expectedDuration = Duration.getInstance(7, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration7());
         expectedDuration = Duration.getInstance(8, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration8());
         expectedDuration = Duration.getInstance(9, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration9());
         expectedDuration = Duration.getInstance(10, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getDuration10());
         // duration variance
         expectedDuration = Duration.getInstance(1, TimeUnit.WEEKS);
         assertEquals(expectedDuration, task.getDurationVariance());
         // early start and finish
         assertEquals(expectedStart, df.format(task.getEarlyStart()));
         assertEquals(expectedFinish, df.format(task.getEarlyFinish()));
         // effort driven
         assertTrue("Effort Driven does not match", task.getEffortDriven()); // shold return true
         // custom start columns
         assertEquals("25/08/2006", df.format(task.getStart1()));
         assertEquals("26/08/2006", df.format(task.getStart2()));
         assertEquals("27/08/2006", df.format(task.getStart3()));
         assertEquals("28/08/2006", df.format(task.getStart4()));
         assertEquals("29/08/2006", df.format(task.getStart5()));
         assertEquals("30/08/2006", df.format(task.getStart6()));
         assertEquals("31/08/2006", df.format(task.getStart7()));
         assertEquals("01/09/2006", df.format(task.getStart8()));
         assertEquals("02/09/2006", df.format(task.getStart9()));
         assertEquals("03/09/2006", df.format(task.getStart10()));
         // custom finish columns
         assertEquals("25/08/2006", df.format(task.getFinish1()));
         assertEquals("26/08/2006", df.format(task.getFinish2()));
         assertEquals("27/08/2006", df.format(task.getFinish3()));
         assertEquals("28/08/2006", df.format(task.getFinish4()));
         assertEquals("29/08/2006", df.format(task.getFinish5()));
         assertEquals("30/08/2006", df.format(task.getFinish6()));
         assertEquals("31/08/2006", df.format(task.getFinish7()));
         assertEquals("01/09/2006", df.format(task.getFinish8()));
         assertEquals("02/09/2006", df.format(task.getFinish9()));
         assertEquals("03/09/2006", df.format(task.getFinish10()));
         // finish slack
         expectedDuration = Duration.getInstance(0, TimeUnit.WEEKS); // use for Finish Slack, Start Slack, Free Slack
         assertEquals(expectedDuration, task.getFinishSlack());
         // start slack
         assertEquals(expectedDuration, task.getStartSlack());
         // free slack
         assertEquals(expectedDuration, task.getFreeSlack());
         // finish variance
         expectedDuration = Duration.getInstance(0, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getFinishVariance());
         // fixed cost accrual
         assertEquals(AccrueType.START, task.getFixedCostAccrual());
         // custom flag columns
         boolean expectedValue = true;
         assertEquals(expectedValue, task.getFlag1());
         assertEquals(expectedValue, task.getFlag2());
         assertEquals(expectedValue, task.getFlag3());
         assertEquals(expectedValue, task.getFlag4());
         assertEquals(expectedValue, task.getFlag5());
         assertEquals(expectedValue, task.getFlag6());
         assertEquals(expectedValue, task.getFlag7());
         assertEquals(expectedValue, task.getFlag8());
         assertEquals(expectedValue, task.getFlag9());
         assertEquals(expectedValue, task.getFlag10());
         assertEquals(expectedValue, task.getFlag11());
         assertEquals(expectedValue, task.getFlag12());
         assertEquals(expectedValue, task.getFlag13());
         assertEquals(expectedValue, task.getFlag14());
         assertEquals(expectedValue, task.getFlag15());
         assertEquals(expectedValue, task.getFlag16());
         assertEquals(expectedValue, task.getFlag17());
         assertEquals(expectedValue, task.getFlag18());
         assertEquals(expectedValue, task.getFlag19());
         assertEquals(expectedValue, task.getFlag20());
         // hide bar
         assertEquals(expectedValue, task.getHideBar());
         // hyperlink
         final String expectedHyperlink = "http://www.steelray.com";
         assertEquals(expectedHyperlink, (task.getHyperlink()));
         // hyperlink address
         assertEquals(expectedHyperlink, (task.getHyperlinkAddress()));
         // ignore resource calendar
         // (note that 'false' is the default value, so this isn't really a test of MPXJ -
         // I couldn't change the value in Project, though)
         assertEquals(false, task.getIgnoreResourceCalendar());
         // late start and finish
         assertEquals(expectedStart, df.format(task.getLateStart()));
         assertEquals(expectedFinish, df.format(task.getLateFinish()));
         // leveling
         assertEquals(false, task.getLevelAssignments());
         assertEquals(false, task.getLevelingCanSplit());
         // leveling delay
         expectedDuration = Duration.getInstance(0, TimeUnit.ELAPSED_DAYS);
         assertEquals(expectedDuration, task.getLevelingDelay());
         // marked
         assertTrue(task.getMarked());
         // milestone
         assertTrue(task.getMilestone());
         // Notes
         assertEquals("Notes Example\n", task.getNotes());
         // custom number columns
         assertEquals(new Double(1), task.getNumber1());
         assertEquals(new Double(2), task.getNumber2());
         assertEquals(new Double(3), task.getNumber3());
         assertEquals(new Double(4), task.getNumber4());
         assertEquals(new Double(5), task.getNumber5());
         assertEquals(new Double(6), task.getNumber6());
         assertEquals(new Double(7), task.getNumber7());
         assertEquals(new Double(8), task.getNumber8());
         assertEquals(new Double(9), task.getNumber9());
         assertEquals(new Double(10), task.getNumber10());
         assertEquals(new Double(11), task.getNumber11());
         assertEquals(new Double(12), task.getNumber12());
         assertEquals(new Double(13), task.getNumber13());
         assertEquals(new Double(14), task.getNumber14());
         assertEquals(new Double(15), task.getNumber15());
         assertEquals(new Double(16), task.getNumber16());
         assertEquals(new Double(17), task.getNumber17());
         assertEquals(new Double(18), task.getNumber18());
         assertEquals(new Double(19), task.getNumber19());
         assertEquals(new Double(20), task.getNumber20());
         // outline level
         assertEquals(new Integer(1), task.getOutlineLevel());
         // outline codes
         assertEquals("1", task.getOutlineNumber());
         assertEquals("1", task.getOutlineCode1());
         assertEquals("A", task.getOutlineCode2());
         assertEquals("a", task.getOutlineCode3());
         assertEquals("Aa", task.getOutlineCode4());
         assertEquals("5", task.getOutlineCode5());
         assertEquals("6", task.getOutlineCode6());
         assertEquals("7", task.getOutlineCode7());
         assertEquals("8", task.getOutlineCode8());
         assertEquals("9", task.getOutlineCode9());
         assertEquals("10", task.getOutlineCode10());
         // priority
         assertEquals(Priority.getInstance(600), task.getPriority());
         // remaining work
         expectedDuration = Duration.getInstance(22, TimeUnit.HOURS);
         assertEquals(expectedDuration, task.getRemainingWork());
         // remaining duration
         expectedDuration = Duration.getInstance(0.55, TimeUnit.WEEKS);
         assertEquals(expectedDuration, task.getRemainingDuration());
         // resume
         assertEquals("25/08/2006", df.format(task.getResume()));
         // rollup
         assertTrue(task.getRollup());
         // start slack
         expectedDuration = Duration.getInstance(0, TimeUnit.WEEKS);
         assertEquals(expectedDuration, task.getStartSlack());
         // start variance
         expectedDuration = Duration.getInstance(0, TimeUnit.DAYS);
         assertEquals(expectedDuration, task.getStartVariance());
         // stop
         assertEquals("25/08/2006", df.format(task.getStop()));
         // total slack
         expectedDuration = Duration.getInstance(0, TimeUnit.WEEKS);
         assertEquals(expectedDuration, task.getTotalSlack());
         // custom text columns
         assertEquals("1", task.getText1());
         assertEquals("2", task.getText2());
         assertEquals("3", task.getText3());
         assertEquals("4", task.getText4());
         assertEquals("5", task.getText5());
         assertEquals("6", task.getText6());
         assertEquals("7", task.getText7());
         assertEquals("8", task.getText8());
         assertEquals("9", task.getText9());
         assertEquals("10", task.getText10());
         assertEquals("11", task.getText11());
         assertEquals("12", task.getText12());
         assertEquals("13", task.getText13());
         assertEquals("14", task.getText14());
         assertEquals("15", task.getText15());
         assertEquals("16", task.getText16());
         assertEquals("17", task.getText17());
         assertEquals("18", task.getText18());
         assertEquals("19", task.getText19());
         assertEquals("20", task.getText20());
         assertEquals("21", task.getText21());
         assertEquals("22", task.getText22());
         assertEquals("23", task.getText23());
         assertEquals("24", task.getText24());
         assertEquals("25", task.getText25());
         assertEquals("26", task.getText26());
         assertEquals("27", task.getText27());
         assertEquals("28", task.getText28());
         assertEquals("29", task.getText29());
         assertEquals("30", task.getText30());
         // unique id
         assertEquals(new Integer(1), task.getUniqueID());
         // wbs
         assertEquals("1", task.getWBS());
         // work variance
         expectedDuration = Duration.getInstance(40, TimeUnit.HOURS);
         assertEquals(expectedDuration, task.getWorkVariance());
     }
      


      /**
       * Tests fields related to Baseline information, as well as actual 
       * dates, estimated, and other fields (see below).
       * 
       * @param mpp The ProjectFile being tested.
       * @throws Exception
       *
       * <br><br>
       * Columns tested:<br><br>
       *
       * Actual Start<br>
       * Actual Finish<br>
       * Baseline Start<br>
       * Baseline Finish<br>
       * Baseline Duration<br>
       * Baseline Work<br>
       * Estimated<br>
       * Predecessors<br>
       * Summary<br>
       * Outline Number<br>
       * WBS<br>
       *
       */
      private void testBaselineTasks(ProjectFile mpp) 
         throws Exception 
      {
          /**
           * Columns tested:
           *
           * Actual Start
           * Actual Finish
           * Baseline Start
           * Baseline Finish
           * Baseline Duration
           * Baseline Work
           * Estimated
           * Predecessors
           * Summary
           * Outline Number
           * WBS
           */

          DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");

          List listAllTasks = mpp.getAllTasks();
          List listAllResources = mpp.getAllResources();
          assertTrue(listAllTasks != null);
          assertTrue(listAllResources != null);
          assertTrue(listAllTasks.size() > 0);
          assertTrue(listAllResources.size() > 0);

          Task baseTask, subtask1, subtask2, subtask3, subtask4, subtask5, completeTask,
                  complexOutlineNumberTask, subtaskA, subtaskA1, subtaskA2, subtaskB, subtaskB1, subtaskB1a;

          // verify that the get()s match the right tasks
          baseTask = (Task)listAllTasks.get(1);
          assertEquals("Base Task", baseTask.getName());
          subtask1 = (Task)listAllTasks.get(2);
          assertEquals("Subtask 1", subtask1.getName());
          subtask2 = (Task)listAllTasks.get(3);
          assertEquals("Subtask 2", subtask2.getName());
          subtask3 = (Task)listAllTasks.get(4);
          assertEquals("Subtask 3", subtask3.getName());
          subtask4 = (Task)listAllTasks.get(5);
          assertEquals("Subtask 4", subtask4.getName());
          subtask5 = (Task)listAllTasks.get(6);
          assertEquals("Subtask 5", subtask5.getName());
          completeTask = (Task)listAllTasks.get(7);
          assertEquals("Complete", completeTask.getName());
          complexOutlineNumberTask = (Task)listAllTasks.get(8);
          assertEquals("Complex Outline Number", complexOutlineNumberTask.getName());
          subtaskA = (Task)listAllTasks.get(9);
          assertEquals("Subtask A", subtaskA.getName());
          subtaskA1 = (Task)listAllTasks.get(10);
          assertEquals("Subtask A1", subtaskA1.getName());
          subtaskA2 = (Task)listAllTasks.get(11);
          assertEquals("Subtask A2", subtaskA2.getName());
          subtaskB = (Task)listAllTasks.get(12);
          assertEquals("Subtask B", subtaskB.getName());
          subtaskB1 = (Task)listAllTasks.get(13);
          assertEquals("Subtask B1", subtaskB1.getName());
          subtaskB1a = (Task)listAllTasks.get(14);
          assertEquals("Subtask B1a", subtaskB1a.getName());

          // Baseline for 'Base Task'
          assertEquals("24/08/2006", df.format(baseTask.getBaselineStart()));
          assertEquals("13/09/2006", df.format(baseTask.getBaselineFinish()));

          // Actual for 'Base Task'
          assertEquals("24/08/2006", df.format(baseTask.getActualStart()));
          assertEquals(null, baseTask.getActualFinish());

          // % Complete
          assertEquals(new Double(57), baseTask.getPercentageComplete());
          // Type for 'Base Task'
          assertEquals(TaskType.FIXED_DURATION, baseTask.getType());

          // Test 'Subtask 2' baseline opposed to planned and actual
          // Planned for 'Subtask 2'
          assertEquals("30/08/2006", df.format(subtask2.getStart()));
          assertEquals("05/09/2006", df.format(subtask2.getFinish()));

          // Actual for 'Subtask 2'
          assertEquals("30/08/2006", df.format(subtask2.getActualStart()));
          assertEquals("05/09/2006", df.format(subtask2.getActualFinish()));

          // Baseline for 'Subtask 2'
          assertEquals("29/08/2006", df.format(subtask2.getBaselineStart()));
          assertEquals("01/09/2006", df.format(subtask2.getBaselineFinish()));

          // Predecessor for Subtask 2
          List predecessors = subtask2.getPredecessors();
          assertEquals(1, predecessors.size());
          Relation relation = (Relation)predecessors.get(0);
          // check task id that's stored in the Relation
          assertEquals(subtask1.getID(), relation.getTaskID());
          // check task unique id that's stored in the Relation
          assertEquals(subtask1.getUniqueID(), relation.getTaskUniqueID());
          // check task id stored in the Task that's stored in the relation
          Task predTask = relation.getTask();
          assertEquals(predTask.getID(), subtask1.getID());
          // check task unique id stored in the Task that's stored in the relation
          assertEquals(predTask.getUniqueID(), subtask1.getUniqueID());
          // Type for 'Subtask 2'
          assertEquals(TaskType.FIXED_UNITS, subtask2.getType());

          // Predecessors for Subtask 5 (multiple preds)
          predecessors = subtask5.getPredecessors();
          assertEquals(2, predecessors.size());
          relation = (Relation)predecessors.get(0);
          Relation relation2 = (Relation)predecessors.get(1);
          assertEquals(subtask3.getID(), relation.getTaskID());
          assertEquals(subtask4.getID(), relation2.getTaskID());
          // Type for subtask 5
          assertEquals(TaskType.FIXED_WORK, subtask5.getType());

          // Summary for 'Subtask A1'
          assertTrue(subtaskA.getSummary());

          // Estimated for 'Subtask A1'
          assertTrue(subtaskA1.getEstimated());
          // Outline Number and WBS for 'Subtask A1'
          String outlineNumber = "2.1.1";
          assertEquals(outlineNumber, subtaskA1.getOutlineNumber());
          assertEquals(outlineNumber, subtaskA1.getWBS());

          // Outline Number and WBS for 'Subtask B1a'
          outlineNumber = "2.2.1.1";
          assertEquals(outlineNumber, subtaskB1a.getOutlineNumber());
          assertEquals(outlineNumber, subtaskB1a.getWBS());
      }


    /**
     * Tests Split Tasks.
     * 
     * @param mpp MPP file
     */
    private void testSplitTasks(ProjectFile mpp) 
    {
        Task task1  = mpp.getTaskByID(new Integer(1));
        Task task2  = mpp.getTaskByID(new Integer(2));

        List listSplits1 = task1.getSplits();
        List listSplits2 = task2.getSplits();

        assertEquals(3, listSplits1.size());
        assertEquals(5, listSplits2.size());

        Duration duration = (Duration)listSplits1.get(0);
        assertEquals(32, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits1.get(1);
        assertEquals(56, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits1.get(2);
        assertEquals(104, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());

        duration = (Duration)listSplits2.get(0);
        assertEquals(24, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits2.get(1);
        assertEquals(40, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits2.get(2);
        assertEquals(80, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits2.get(3);
        assertEquals(104, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
        duration = (Duration)listSplits2.get(4);
        assertEquals(160, (int)duration.getDuration());
        assertEquals(TimeUnit.HOURS, duration.getUnits());
    }

    /**
     * Tests Relations.
     * 
     * @param mpp mpp file
     */
    private void testRelations(ProjectFile mpp) 
    {
        List listAllTasks = mpp.getAllTasks();
        assertTrue(listAllTasks != null);

        Task task1 = mpp.getTaskByID(new Integer(1));
        Task task2 = mpp.getTaskByID(new Integer(2));
        Task task3 = mpp.getTaskByID(new Integer(3));
        Task task4 = mpp.getTaskByID(new Integer(4));
        Task task5 = mpp.getTaskByID(new Integer(5));

        List listPreds = task2.getPredecessors();
        Relation relation = (Relation)listPreds.get(0);
        assertEquals(1, relation.getTaskID().intValue());
        assertEquals(1, relation.getTaskUniqueID().intValue());
        assertEquals(RelationType.FINISH_START, relation.getType());
        assertEquals(task1, relation.getTask());

        listPreds = task3.getPredecessors();
        relation = (Relation)listPreds.get(0);
        assertEquals(2, relation.getTaskID().intValue());
        assertEquals(2, relation.getTaskUniqueID().intValue());
        assertEquals(RelationType.START_START, relation.getType());
        Duration duration = relation.getDuration();
        assertEquals(1, (int)duration.getDuration());
        assertEquals(TimeUnit.DAYS, duration.getUnits());

        listPreds = task4.getPredecessors();
        relation = (Relation)listPreds.get(0);
        assertEquals(3, relation.getTaskID().intValue());
        assertEquals(3, relation.getTaskUniqueID().intValue());
        assertEquals(RelationType.FINISH_FINISH, relation.getType());

        listPreds = task5.getPredecessors();
        relation = (Relation)listPreds.get(0);
        assertEquals(4, relation.getTaskID().intValue());
        assertEquals(4, relation.getTaskUniqueID().intValue());
        assertEquals(RelationType.START_FINISH, relation.getType());
    }

}
