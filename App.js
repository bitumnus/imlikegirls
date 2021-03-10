import React from 'react';
import 'react-native-gesture-handler';
import { StyleSheet } from 'react-native';
import Site from './components/Site';

export default function App() {

  return (
      <Site style={styles.container} deep='imlikegirls.page.link/sQXj' />
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
