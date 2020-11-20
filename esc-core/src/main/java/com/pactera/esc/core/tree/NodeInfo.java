/**
 * LICENSE
 *
 * This source file is subject to the MIT license that is bundled
 * with this package in the file MIT.txt.
 * It is also available through the world-wide-web at this URL:
 * http://www.opensource.org/licenses/mit-license.html
 */

package com.pactera.esc.core.tree;

/**
 * A NodeInfo implementor carries information about the identity and position
 * of a node in a nested set tree.
 */
public interface NodeInfo {
    String getId();
    int getLeftValue();
    int getRightValue();
    int getLevel();
    int getRootValue();
    void setLeftValue(int value);
    void setRightValue(int value);
    void setLevel(int level);
    void setRootValue(int value);
}
