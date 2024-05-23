package util;

// 배열을 다룰 때 도움이 될 메소드들을 모아둔 클래스
public class ArrayUtil {
    // 1. int[]
    // 1-A. 현재 배열의 길이를 리턴하는 size()
    public static int size(int[] array) {
        return array.length;
    }

    // 1-B. 현재 배열이 아무것도 없으면 true, 있으면 false가 리턴되는
    // isEmpty()
    public static boolean isEmpty(int[] array) {
        return size(array) == 0;
    }

    // 1-C. 현재 배열의 특정 인덱스의 값을 리턴하는
    // get()
    public static int get(int[] array, int index) {
        return array[index];
    }

    // 1-D. 현재 배열에서 특정 값의 가장 먼저 등장하는 인덱스를 찾는
    // indexOf()
    // 단, 해당 배열에 그 값이 없으면 -1이 리턴된다.
    public static int indexOf(int[] array, int element) {
        for (int i = 0; i < size(array); i++) {
            if (element == get(array, i)) {
                return i;
            }
        }

        return -1;
    }

    // 1-E. 현재 배열에서 특정 값의 가장 마지막에 등장하는 인덱스를 찾는
    // lastIndexOf()
    // 단, 해당 배열에 그 값이 없으면 -1이 리턴된다.
    public static int lastIndexOf(int[] array, int element) {
        for (int i = size(array) - 1; i >= 0; i--) {
            if (element == get(array, i)) {
                return i;
            }
        }
        return -1;
    }

    // 1-F. 현재 배열에서 특정한 값이 존재하면 true, 없으면 false가 나오는
    // contains()
    public static boolean contains(int[] array, int element) {
        return indexOf(array, element) != -1;
    }

    // 1-G. 현재 배열에 맨 마지막에 새로운 요소를 추가하는
    // add()
    public static int[] add(int[] array, int element) {
        // array보다 길이가 1이 더 큰 새로운 배열을 하나 만들어준다.
        int[] temp = new int[size(array) + 1];
        // 원본 배열인 array의 값들을 temp에 복사해온다.
        for (int i = 0; i < size(array); i++) {
            temp[i] = get(array, i);
        }
        // 새로운 요소인 element를 temp의 가장 마지막 칸에 저장한다.
        temp[size(temp) - 1] = element;

        // temp를 리턴한다.
        return temp;
    }

    // 1-H. 현재 배열의 특정 인덱스에 새로운 값을 추가하는
    // add()
    public static int[] add(int[] array, int index, int element) {
        // 만약 index가 불가능한 index일 경우,
        // 원본 array를 다시 리턴을 한다.
        if (index < 0 || index > size(array)) {
            return array;
        }

        int[] temp = new int[0];
        for (int i = 0; i < size(array) + 1; i++) {
            if (i < index) {
                temp = add(temp, get(array, i));
            } else if (i == index) {
                temp = add(temp, element);
            } else {
                temp = add(temp, get(array, i - 1));
            }
        }

        return temp;
    }

    // 1-I. 해당 배열에서 특정 인덱스의 값을 다른 값으로 교체하고
    // 원래 있던 값을 리턴하는
    // set()
    public static int set(int[] array, int index, int element) {
        int temp = get(array, index);
        array[index] = element;

        return temp;
    }

    // 1-J. 해당 배열의 특정 인덱스를 삭제하는
    // remove()
    public static int[] remove(int[] array, int index) {
        int[] temp = new int[0];

        for (int i = 0; i < size(array); i++) {
            if (i != index) {
                temp = add(temp, get(array, i));
            }
        }

        return temp;
    }

    // 1-K. 해당 배열의 특정 엘리먼트가 있는 인덱스를 삭제하는
    // removeElement()
    public static int[] removeElement(int[] array, int element) {
        return remove(array, indexOf(array, element));
    }

    // 2. String[]
    // 2-A. size()
    public static int size(String[] array) {
        return array.length;
    }

    // 2-B. isEmpty()
    public static boolean isEmpty(String[] array) {
        return size(array) == 0;
    }

    // 2-C. get()
    public static String get(String[] array, int index) {
        return array[index];
    }

    // 2-D. indexOf()
    public static int indexOf(String[] array, String element) {
        for (int i = 0; i < size(array); i++) {
            if (element.equals(get(array, i))) {
                return i;
            }
        }
        return -1;
    }

    // 2-E. lastIndexOf()
    public static int lastIndexOf(String[] array, String element) {
        for (int i = size(array) - 1; i >= 0; i--) {
            if (element.equals(get(array, i))) {
                return i;
            }
        }
        return -1;
    }

    // 2-F. contains()
    public static boolean contains(String[] array, String element) {
        return indexOf(array, element) != -1;
    }

    // 2-G. add()
    public static String[] add(String[] array, String element) {
        String[] temp = new String[size(array) + 1];
        for (int i = 0; i < size(array); i++) {
            temp[i] = get(array, i);
        }
        temp[size(temp) - 1] = element;

        return temp;
    }

    // 2-H. add()
    public static String[] add(String[] array, int index, String element) {
        if (index < 0 || index >= size(array)) {
            return array;
        }

        String[] temp = new String[0];

        for (int i = 0; i < size(array) + 1; i++) {
            if (i < index) {
                temp = add(temp, get(array, i));
            } else if (i == index) {
                temp = add(temp, element);
            } else {
                temp = add(temp, get(array, i - 1));
            }
        }

        return temp;
    }

    // 2-I. set()
    public static String set(String[] array, int index, String element) {
        String temp = get(array, index);
        array[index] = element;
        return temp;
    }

    // 2-J. remove()
    public static String[] remove(String[] array, int index) {
        String[] temp = new String[0];
        for (int i = 0; i < size(array); i++) {
            if (i != index) {
                temp = add(temp, get(array, i));
            }
        }

        return temp;
    }

    // 2-K. remove()
    public static String[] remove(String[] array, String element) {
        return remove(array, indexOf(array, element));
    }
}










