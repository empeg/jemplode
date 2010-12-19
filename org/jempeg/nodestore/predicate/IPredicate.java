/**
* Copyright (c) 2001, Mike Schrag & Daniel Zimmerman
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
*
* Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* Neither the name of Mike Schrag, Daniel Zimmerman, nor the names of any
* other contributors may be used to endorse or promote products derived from
* this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
* TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*/
package org.jempeg.nodestore.predicate;

import org.jempeg.nodestore.IFIDNode;
import org.jempeg.nodestore.model.NodeTag;

/**
 * IPredicate is the interface for predicates.  A predicate
 * is a single clause in a query string.
 * 
 * @author Mike Schrag
 * @version $Revision: 1.3 $
 */
public interface IPredicate {
	public String getValue(IFIDNode _node);

	/**
	 * Evaluate the predicate on the given node.
	 * 
	 * @param _node the node to evaluate this predicate on
	 */
  public boolean evaluate(IFIDNode _node);
  
  /**
   * Returns whether or not this predicate is affected by a change in a tag of
   * the given name.
   * 
   * @param _tag the tag to check
   * @return whether or not this predicate is dependent on _tag
   */
  public boolean isDependentOn(NodeTag _tag);
}