/*
 * file:       FieldTypeUtility.java
 * author:     Jon Iles
 * copyright:  (c) Packwood Software 2011
 * date:       2011-05-17
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

package net.sf.mpxj.utility;

import java.util.Locale;

import net.sf.mpxj.AssignmentField;
import net.sf.mpxj.ConstraintField;
import net.sf.mpxj.DataType;
import net.sf.mpxj.FieldType;
import net.sf.mpxj.MPPAssignmentField;
import net.sf.mpxj.MPPAssignmentField14;
import net.sf.mpxj.MPPConstraintField;
import net.sf.mpxj.MPPResourceField;
import net.sf.mpxj.MPPResourceField14;
import net.sf.mpxj.MPPTaskField;
import net.sf.mpxj.MPPTaskField14;
import net.sf.mpxj.ResourceField;
import net.sf.mpxj.TaskField;

/**
 * Utility class containing methods relating to the FieldType class. 
 */
public final class FieldTypeUtility
{
   /**
    * Retrieve a FieldType instance based on an ID value from 
    * an MPP9 or MPP12 file.
    * 
    * @param fieldID field ID 
    * @return FieldType instance
    */
   public static final FieldType getInstance(int fieldID)
   {
      FieldType result;
      int prefix = fieldID & 0xFFFF0000;
      int index = fieldID & 0x0000FFFF;

      switch (prefix)
      {
         case MPPTaskField.TASK_FIELD_BASE:
         {
            result = MPPTaskField.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(TaskField.class, index);
            }
            break;
         }

         case MPPResourceField.RESOURCE_FIELD_BASE:
         {
            result = MPPResourceField.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(ResourceField.class, index);
            }
            break;
         }

         case MPPAssignmentField.ASSIGNMENT_FIELD_BASE:
         {
            result = MPPAssignmentField.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(AssignmentField.class, index);
            }
            break;
         }

         case MPPConstraintField.CONSTRAINT_FIELD_BASE:
         {
            result = MPPConstraintField.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(ConstraintField.class, index);
            }
            break;
         }

         default:
         {
            result = getPlaceholder(null, index);
            break;
         }
      }

      return result;
   }

   /**
    * Retrieve a FieldType instance based on an ID value from 
    * an MPP14 field, mapping the START_TEXT, FINISH_TEXT, and DURATION_TEXT
    * values to START, FINISH, and DURATION respectively.
    * 
    * @param fieldID field ID 
    * @return FieldType instance
    */
   public static final FieldType getInstance14(int fieldID)
   {
      FieldType result;
      int prefix = fieldID & 0xFFFF0000;
      int index = fieldID & 0x0000FFFF;

      switch (prefix)
      {
         case MPPTaskField.TASK_FIELD_BASE:
         {
            result = MPPTaskField14.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(TaskField.class, index);
            }
            break;
         }

         case MPPResourceField.RESOURCE_FIELD_BASE:
         {
            result = MPPResourceField14.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(ResourceField.class, index);
            }
            break;
         }

         case MPPAssignmentField.ASSIGNMENT_FIELD_BASE:
         {
            result = MPPAssignmentField14.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(AssignmentField.class, index);
            }
            break;
         }

         case MPPConstraintField.CONSTRAINT_FIELD_BASE:
         {
            result = MPPConstraintField.getInstance(index);
            if (result == null)
            {
               result = getPlaceholder(ConstraintField.class, index);
            }
            break;
         }

         default:
         {
            result = getPlaceholder(null, index);
            break;
         }
      }

      return result;
   }

   /**
    * Generate a placeholder for an unknown type.
    * 
    * @param type expected type
    * @param fieldID field ID
    * @return placeholder
    */
   private static FieldType getPlaceholder(final Class<?> type, final int fieldID)
   {
      return new FieldType()
      {

         @Override public int getValue()
         {
            return fieldID;
         }

         @Override public String getName()
         {
            return "Unknown " + (type == null ? "" : type.getSimpleName() + "(" + fieldID + ")");
         }

         @Override public String getName(Locale locale)
         {
            return getName();
         }

         @Override public DataType getDataType()
         {
            return null;
         }

         @Override public FieldType getUnitsType()
         {
            return null;
         }

         @Override public String toString()
         {
            return getName();
         }
      };
   }
}
